import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ActivatedRoute, Router} from '@angular/router';



@Component({
  selector: 'app-nurse-finished-appointments',
  templateUrl: './nurse-finished-appointments.component.html',
  styleUrls: ['./nurse-finished-appointments.component.css']
})
export class NurseFinishedAppointmentsComponent implements OnInit {

  displayedColumns: string[] = ['date', 'time', 'duration', 'price', 'doctor', 'hall', 'appointmentType', 'view'];
  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private _snackBar: MatSnackBar, private router: Router, private http: HttpClient,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8081/nurse/getFinishedAppointments")
      .subscribe((res) => {
        console.log(res);
        // @ts-ignore
        this.dataSource.data = res;
      }, (err)=>{
        this._snackBar.open("Something went wrong!", "Close", {
          duration: 2000,
        });
        }
      );

    this.dataSource.paginator = this.paginator;
  }


  viewReceipt(element: any) {
    this.router.navigate(['/staff/authenticateMedicine'],{queryParams : {'apt_id' : element.id}});
  }
}
