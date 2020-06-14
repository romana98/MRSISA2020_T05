import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient, HttpParams} from "@angular/common/http";
import {MatDialog} from "@angular/material/dialog";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-view-predefined-appointments',
  templateUrl: './view-predefined-appointments.component.html',
  styleUrls: ['./view-predefined-appointments.component.css']
})
export class ViewPredefinedAppointmentsComponent implements OnInit {

  displayedColumns: string[] = ['date', 'time', 'duration', 'price', 'doctor', 'hall', 'appointmentType', 'reserve'];

  dataSource = new MatTableDataSource();

  selectedRowIndex: number = 0;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  model : appointmentModel = {
    id: null,
    date : '',
    duration : 0,
    price : 0,
    doctor : null,
    hall : null,
    appointmentType: null
  };

  constructor(private _snackBar: MatSnackBar,private router: ActivatedRoute, private http: HttpClient, private navigate_router: Router) { }

  ngOnInit(): void {
    let params = new HttpParams();
    params = params.append('clinicId', this.router.snapshot.queryParamMap.get('clinicId'));
    this.http.get("https://eclinic05.herokuapp.com/appointment/getClinicPredefinedAppointments",{params:params})
      .subscribe((res) => {
        // @ts-ignore
        this.dataSource.data = res;
      });

    this.dataSource.paginator = this.paginator;
  }

  Reserve(req) {
    let url = "https://eclinic05.herokuapp.com/appointment/reservePredefinedAppointment"
    this.http.post(url, req).subscribe(
      res => {
        let index = this.dataSource.data.indexOf(req);
        this.dataSource.data.splice(index, 1);
        this.dataSource._updateChangeSubscription()
        this._snackBar.open("Appointment reserved", "Close", {
          duration: 2000,
        });

      },
      err => {
          this._snackBar.open("Error has occurred while reserving an appointment", "Close", {
            duration: 2000,
          });
          console.log(err);
      }
    );

  }
}


export interface appointmentModel
{
  id: number;
  date : string;
  duration : number;
  price : number;
  doctor : { name:string, surname:string };
  hall : { name:string, number:number };
  appointmentType: { name:string };

}
