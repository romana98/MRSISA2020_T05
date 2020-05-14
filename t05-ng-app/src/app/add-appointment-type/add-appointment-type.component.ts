import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';


@Component({
  selector: 'app-add-appointment-type',
  templateUrl: './add-appointment-type.component.html',
  styleUrls: ['./add-appointment-type.component.css']
})
export class AddAppointmentTypeComponent implements OnInit {

  displayedColumns: string[] = ['name', 'delete'];

  dataSource = new MatTableDataSource();

  selectedRowIndex: number = 0;

  model : AppointmentTypeModel = {
    name : '',
    admin_id : parseInt(sessionStorage.getItem('id')),
    id : 0
  }

  clinic_id : any = '';

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  constructor(private _snackBar: MatSnackBar, private http: HttpClient, private changeDetectorRefs: ChangeDetectorRef) { }

  ngOnInit(): void {

    let params1 = new HttpParams().set('admin_id',sessionStorage.getItem('user_id').toString())

    this.http.get("http://localhost:8081/appointmentType/getAppointmentTypes",{params:params1}).subscribe(
      res => {
        // @ts-ignore
        this.dataSource.data = res;

        });
      this.dataSource.paginator = this.paginator;
  }

  addAppointmentType() : void {
    let url = "http://localhost:8081/appointmentType/addAppointmentType";
    this.model.admin_id = parseInt(sessionStorage.getItem('user_id'));
    console.log(this.model.admin_id);
    this.http.post(url,this.model).subscribe(
      res => {
        //poziv kako bismo nakon dodavanja appointmenta dobili novije podatke u tabeli
        let params1 = new HttpParams().set('admin_id',sessionStorage.getItem('user_id').toString())
        this.http.get("http://localhost:8081/appointmentType/getAppointmentTypes",{params:params1}).subscribe(
          res => {
        // @ts-ignore
              this.dataSource.data = res;

        });

        this._snackBar.open("Appointment type added successfully.", "Close", {
          duration: 2000,
        });
      },
      err => {
        this._snackBar.open("Error has occurred while adding appointment type", "Close", {
          duration: 2000,
        });
        console.log(err);
      });
    }

    checkValidation() : boolean {
      return (/^[a-zA-z]+$/.test(this.model.name));

    }

    deleteAppointmentType(element){
      let params = new HttpParams().set("aptype_id", element.id.toString());
      this.http.delete("http://localhost:8081/appointmentType/deleteAppointmentType",{params:params}).subscribe(
        res =>{
          let index = this.dataSource.data.indexOf(element);
          this.dataSource.data.splice(index,1);
          this.dataSource._updateChangeSubscription();
          this._snackBar.open("Appointment type deleted successfully.", "Close", {
          duration: 2000,
          });
        }

      );
    }

}


export interface AppointmentTypeModel{
    name: string;
    admin_id : number;
    id : number;
}

