import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatExpansionPanel } from '@angular/material/expansion';


@Component({
  selector: 'app-add-appointment-type',
  templateUrl: './add-appointment-type.component.html',
  styleUrls: ['./add-appointment-type.component.css']
})
export class AddAppointmentTypeComponent implements OnInit {

  displayedColumns: string[] = ['name', 'delete'];

  dataSource = new MatTableDataSource();

  selectedRowIndex: number = 0;

  isDisabled : boolean = true;

  isOpen : boolean = false;

  model : AppointmentTypeModel = {
    name : '',
    admin_id : parseInt(sessionStorage.getItem('id')),
    id : 0
  }

  currentlySelected : AppointmentTypeModel = {
    name : '',
    admin_id : parseInt(sessionStorage.getItem('id')),
    id : 0
  }

  clinic_id : any = '';

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild('expanel') expanel: MatExpansionPanel;



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
      this.currentlySelected.admin_id = -1;
      this.currentlySelected.id = -1;
      this.currentlySelected.name = '';
      let params = new HttpParams().set("aptype_id", element.id.toString());
      this.http.delete("http://localhost:8081/appointmentType/deleteAppointmentType",{params:params}).subscribe(
        res =>{
          let index = this.dataSource.data.indexOf(element);
          this.dataSource.data.splice(index,1);
          this.dataSource._updateChangeSubscription();
          this.isDisabled = true;
          this.expanel.close();
          this._snackBar.open("Appointment type deleted successfully.", "Close", {
          duration: 2000,
          });
          
        }

      );

    }

    selectionChanged(element){
      this.isDisabled = false;
      this.expanel.open();
      this.currentlySelected.admin_id = element.admin_id;
      this.currentlySelected.id = element.id;
      this.currentlySelected.name = element.name;
    }

    editSubbmited() {
      this.http.post("http://localhost:8081/appointmentType/editAppointmentType", this.currentlySelected).subscribe(
        res =>{

          let params1 = new HttpParams().set('admin_id',sessionStorage.getItem('user_id').toString())
          this.http.get("http://localhost:8081/appointmentType/getAppointmentTypes",{params:params1}).subscribe(
            res => {
          // @ts-ignore
                this.dataSource.data = res;
  
          });

          this._snackBar.open("Appointment type changed successfully.", "Close", {
          duration: 2000,
          });
        },
        err => {
          this._snackBar.open("Appointment type with that name already exists", "Close", {
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

