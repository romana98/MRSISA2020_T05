import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";


@Component({
  selector: 'request-list-patients',
  templateUrl: './request-list-patients.component.html',
  styleUrls: ['./request-list-patients.component.css']
})
export class RequestListPatientsComponent implements OnInit {
  displayedColumns: string[] = ['email', 'name', 'surname', 'address',
    'city', 'country', 'phone_number', 'insurance_number', 'accept', 'decline'];

  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  text: string;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {


    this.http.get("http://localhost:8081/registrationRequests/getRequests")
      .subscribe((res) => {
        // @ts-ignore
        this.dataSource.data = res;

      });

    this.dataSource.paginator = this.paginator;

  }

  Accept(req): void {

    let url = "http://localhost:8081/patients/addPatient"
    this.http.post(url, req).subscribe(
      res => {
        let index = this.dataSource.data.indexOf(req);
        this.dataSource.data.splice(index, 1);
        this.dataSource._updateChangeSubscription()
        this._snackBar.open("Patient registered", "Close", {
          duration: 2000,
        });

      },
      err => {
        if (err.status == 409) {
          this._snackBar.open("Patient already exists with" + req.email + "email", "Close", {
            duration: 2000,
          });
        } else {
          this._snackBar.open("Error has occurred while registering patient", "Close", {
            duration: 2000,
          });
          console.log(err);
        }
      }
    );
  }

  Decline(req): void {
    const dialogRef = this.dialog.open(DialogOverview, {
      width: '25%',height: '40%',
      data: {text: this.text}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.text = result;

      let url = "http://localhost:8081/registrationRequests/declineRequest"
      this.http.post(url, req, this.text).subscribe(
        res => {
          let index = this.dataSource.data.indexOf(req);
          this.dataSource.data.splice(index, 1);
          this.dataSource._updateChangeSubscription()
          this._snackBar.open("Request declined", "Close", {
            duration: 2000,
          });

        },
        err => {
          if (err.status == 409) {
            this._snackBar.open("Patient already exists with" + req.email + "email", "Close", {
              duration: 2000,
            });
          } else {
            this._snackBar.open("Error has occurred while declining registering patient", "Close", {
              duration: 2000,
            });
            console.log(err);
          }
        }
      );

    });
  }

}

export interface DialogData {
  text: string;
}

@Component({
  selector: 'request-list-patients',
  templateUrl: './dialog-overview.html',
  styleUrls: ['./request-list-patients.component.css']
})
export class DialogOverview {

  constructor(
    public dialogRef: MatDialogRef<DialogOverview>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onClick(): void {
    this.dialogRef.close();
  }

}

