import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";


@Component({
  selector: 'patient-view-appointments',
  templateUrl: './view-appointments.component.html',
  styleUrls: ['./view-appointments.component.css']
})
export class ViewAppointmentsComponent implements OnInit {

  displayedColumns: string[] = ['date', 'time', 'duration', 'price', 'doctor', 'hall', 'appointmentType'];
  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8081/patients/getIncomingAppointments")
      .subscribe((res) => {
        console.log(res);
        // @ts-ignore
        this.dataSource.data = res;

      });

    this.dataSource.paginator = this.paginator;
  }


}
