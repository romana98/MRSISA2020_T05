import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";


@Component({
  selector: 'request-list-leave',
  templateUrl: './request-list-leave.component.html',
  styleUrls: ['./request-list-leave.component.css']
})
export class RequestListLeaveComponent implements OnInit {
  displayedColumns: string[] = ['email', 'name', 'surname', 'startDate',
    'endDate','accept', 'decline'];

  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  text: string;
  sent: boolean;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {


    this.http.get("http://localhost:8081/leaveRequest/getRequests")
      .subscribe((res) => {
        // @ts-ignore
        this.dataSource.data = res;

      });

    this.dataSource.paginator = this.paginator;

  }

  Accept(req): void {

    let url = "http://localhost:8081/medicalStaff/addLeave"
    this.http.post(url, req).subscribe(
      res => {
        let index = this.dataSource.data.indexOf(req);
        this.dataSource.data.splice(index, 1);
        this.dataSource._updateChangeSubscription()
        this._snackBar.open("Leave accepted", "Close", {
          duration: 2000,
        });

      },
      err => {

          this._snackBar.open("Error has occurred while accepting leave request", "Close", {
            duration: 2000,
          });
          console.log(err);
        }
    );
  }

  Decline(req): void {
    const dialogRef = this.dialog.open(DialogOverviewLeave, {
      width: '25%',height: '40%',
      data: {text: this.text, sent:this.sent}
    });

    dialogRef.afterClosed().subscribe(result => {
      req.text = result.text;
      if(req.text == null)
      {
        this._snackBar.open("Message not sent! Sent body can't be empty!", "Close", {
          duration: 2000,
        });
      }

      if(result.sent === true && req.text != null)
      {
        let url = "http://localhost:8081/leaveRequest/declineRequest"
        this.http.post(url, req).subscribe(
          res => {
            let index = this.dataSource.data.indexOf(req);
            this.dataSource.data.splice(index, 1);
            this.dataSource._updateChangeSubscription()
            this._snackBar.open("Request declined!", "Close", {
              duration: 2000,
            });

          },
          err => {

              this._snackBar.open("Error has occurred while declining leave request!", "Close", {
                duration: 2000,
              });
              console.log(err);
          }
        );
      }


    });
  }

}

export interface DialogData {
  text: string;
  sent: boolean;
}

@Component({
  selector: 'request-list-leave',
  templateUrl: './dialog-overview-leave.html',
  styleUrls: ['./request-list-leave.component.css']
})
export class DialogOverviewLeave {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewLeave>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onClick(): boolean {

    return false;
  }

  Send(): boolean{
    return true;
  }

}

