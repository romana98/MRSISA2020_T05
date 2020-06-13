import {Component, OnInit, ViewChild, ChangeDetectorRef, Inject} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { EXPANSION_PANEL_ANIMATION_TIMING, MatExpansionPanel } from '@angular/material/expansion';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';

@Component({
    selector: 'app-add-hall-form',
    templateUrl: './add-hall-form.component.html',
    styleUrls: ['./add-hall-form.component.css']
})
export class AddHallFormComponent implements OnInit{

    displayedColumns: string[] = ['name', 'number','available' , 'reserve', 'delete'];

    dataSource = new MatTableDataSource();

    selectedRowIndex: number = 0;

    isDisabled : boolean = true;

    search_param : String = 'a';
    search_value : String = '';
    date_value : Date = null;
    today: Date;

    model: hallModel = {
        name: '',
        number: 0
    }

    currentlySelected: editHall = {
      name : '',
      number : 0,
      id : 0
    }

    clinic_id : any = '';

    deleteModel : deleteHall = {
        hall_id : 0
    }

    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
    @ViewChild('expanel') expanel: MatExpansionPanel;

    constructor(private _snackBar: MatSnackBar, private http: HttpClient,private changeDetectorRefs: ChangeDetectorRef,public dialog: MatDialog){

    }


    ngOnInit(): void{
      this.today = new Date();
      let date : String;
      date = this.today.getDate() + "/" + (this.today.getMonth()+1) + "/" + this.today.getFullYear();
      this.refreshData(date);
      this.dataSource.paginator = this.paginator;
    }

    refreshData(date){
      this.http.get("http://localhost:8081/halls/getAvailabileHalls",{params:{'clinic_admin_id' : sessionStorage.getItem('user_id'),
      'param_name': "a",
      'param_value' : "-1",
      'date' : date.toString()}}).subscribe(
        res => {
        //@ts-ignore
        this.dataSource.data = res;
      });
    }

    AddHall(): void{
        let url = "http://localhost:8081/halls/addHall"
        this.http.post(url, this.model).subscribe(
            res => {

              //kada dobijem odgovor da sam uspeo da dodam salu hocu da posaljem upit za uzimanje svih sala da bih u tabeli prikazao
              let date : String;
              date = this.today.getDate() + "/" + (this.today.getMonth()+1) + "/" + this.today.getFullYear();
              this.refreshData(date);

            },
          err => {
            if(err.status == 409)
            {
              this._snackBar.open("Hall name/number already taken!", "Close", {
                duration: 2000,
              });

            }
            else
            {
              this._snackBar.open("Error has occurred while adding hall!", "Close", {
                duration: 2000,
              });

            }
          }
        );
    }

    deleteHall(element): void{
        let params = new HttpParams().set("hall_id", element.id.toString());
        this.http.delete("http://localhost:8081/halls/deleteHall",{params:params}).subscribe(
          res =>{

            let index = this.dataSource.data.indexOf(element);
            this.dataSource.data.splice(index,1);
            this.dataSource._updateChangeSubscription();
            this.isDisabled = true;
            this.expanel.close();
            this._snackBar.open("Hall deleted successfully.", "Close", {
            duration: 2000,
            });
          },

          err =>{
                this._snackBar.open("Hall is reserved for appointment and cant be deleted.", "Close", {
                duration: 2000,})
          }

        );

    }

    checkDisable(row){

      if (row.times.length > 0){
        return "true"
      }
      else{
        return "false"
      }
    }

    selectionChanged(element){
      console.log(element.times.length == 0);
      console.log(element);
      this.expanel.open();
      this.isDisabled = false;
      this.currentlySelected.name = element.name;
      this.currentlySelected.number = element.number;
      this.currentlySelected.id = element.id;
    }

    editSubbmited() {
      let params = new HttpParams();
      params = params.append('name', this.currentlySelected.name);
      params = params.append('number', this.currentlySelected.number.toString());
      params = params.append('id' , this.currentlySelected.id.toString());
      console.log(params.get('name'));
      this.http.get("http://localhost:8081/halls/editHall", {params:params}).subscribe(
        res =>{

          let params = new HttpParams().set('clinic_id', this.clinic_id);
          this.http.get("http://localhost:8081/halls/getClinicHall",{params:params})
          .subscribe((res) => {
            // @ts-ignore
            this.dataSource.data = res;
          });

          this._snackBar.open("Hall changed successfully.", "Close", {
          duration: 2000,
          });
        },
        err => {
          this._snackBar.open("Hall with that name and number already exists", "Close", {
            duration: 2000,
          });
        }

      );

    }

    Reserve(element) {
      const dialogRef = this.dialog.open(FirstDialog, {
        width: '50%',height: '70%', data : {
          date: this.today.getDate() + "/" + (this.today.getMonth()+1) + "/" + this.today.getFullYear(),
          hall_id : element.id.toString()
        }});
      dialogRef.afterClosed().subscribe(result => {
        let date : String;
        date = this.date_value.getDate() + "/" + (this.date_value.getMonth()+1) + "/" + this.date_value.getFullYear();
        this.refreshData(date);
      });
      
      }
      

    goSearch(){
      this.today = this.date_value
      console.log(this.today)
      let date : String;
      date = this.date_value.getDate() + "/" + (this.date_value.getMonth()+1) + "/" + this.date_value.getFullYear();
      this.refreshData(date);

    }

}

@Component({
  selector: 'reserve-first-dialog',
  templateUrl: './first-dialog.html',
  styleUrls: ['./add-hall-form.component.css']
})
export class FirstDialog {
  
  displayedColumns: string[] = ['date', 'time','doctor' , 'patient', 'reserve'];

  dataSource = new MatTableDataSource();

  search_param : String = '';

  constructor(
    public dialogRef: MatDialogRef<FirstDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
      private http: HttpClient,
      private _snackBar: MatSnackBar,
      public dialog: MatDialog) {}

  ngOnInit(): void{
    this.refreshData();

  }

  refreshData(): void {
    this.http.get("http://localhost:8081/appointment/getAppointmentRequests",{params:{'clinic_admin_id' : sessionStorage.getItem('user_id'),
    'date' : this.data.date.toString()}}).subscribe(
      res => {
      //@ts-ignore
      this.dataSource.data = res;
    });
  }

  onClick(element) {
    this.http.get("http://localhost:8081/halls/reserveHall",{params:{
      hall_id : this.data.hall_id.toString(),
      appointment_id : element.id.toString(),
      date : this.data.date.toString(),
      clinic_admin_id : sessionStorage.getItem('user_id')
    }}).subscribe(
      res => {
      if(res === 0){
          //@ts-ignore
          this.refreshData();
          this._snackBar.open("Hall reserved successfully!", "Close", {
            duration: 2000,})
      }
      else{
        //@ts-ignore
        const dialogRef = this.dialog.open(SecondDialog, {
          width: '50%',height: '50%', data : {
              appointment_id : element.id.toString(),
              hall_id : this.data.hall_id.toString(),
              clinic_admin_id : sessionStorage.getItem('user_id'),
              //u pitanju je operacija
              type : 1
          }});

        dialogRef.afterClosed().subscribe(result => {
            this.refreshData();
          });
      }
      
    },
      err => {
        if (err.status === 409){
          this._snackBar.open("This hall can't be reserved for that time, you can choose other hall.", "Close", {
            duration: 2000,})
        }
        else{
          if(err === 0){
            
          }
          const dialogRef = this.dialog.open(SecondDialog, {
            width: '50%',height: '50%', data : {
                appointment_id : element.id.toString(),
                hall_id : this.data.hall_id.toString(),
                clinic_admin_id : sessionStorage.getItem('user_id'),
                type : 0
            }});

          dialogRef.afterClosed().subscribe(result => {
              this.refreshData();
            });

          this._snackBar.open("There is no available halls for this appointment date", "Close", {
            duration: 2000,})
        }
        
      }
    );
    
  }
}

@Component({
  selector: 'reserve-second-dialog',
  templateUrl: './second-dialog.html',
  styleUrls: ['./add-hall-form.component.css']
})
export class SecondDialog {
  
  dialog_data : any = null;

  choosen_doc : number = -1;

  show_doctors : boolean = true;

  operation : boolean;

  constructor(
    public dialogRef: MatDialogRef<FirstDialog>,
    @Inject(MAT_DIALOG_DATA) public data: SecondDialogData,
      private http: HttpClient,
      private _snackBar: MatSnackBar) {}

  ngOnInit(): void{
    this.operation = (this.data.type == 1);
    this.http.get("http://localhost:8081/halls/getFirstTime",{params:{'clinic_admin_id' : sessionStorage.getItem('user_id'),
    'appointment_id' : this.data.appointment_id.toString(),
    'hall_id' : this.data.hall_id.toString()}}).subscribe(
      res => {
      //@ts-ignore
      this.dialog_data = res;
      this.show_doctors = this.dialog_data.doctors.length > 0;
    });
  }

  reserve(){
    this.http.get("http://localhost:8081/halls/reserveNewHall",{params:{'hall_id' : this.data.hall_id.toString(),
    'appointment_id' : this.data.appointment_id.toString(),
    'date' : this.dialog_data.date.toString(),
    'doctor_id' : this.choosen_doc.toString()}}).subscribe(
      res => {
      //@ts-ignore
    });
    this.dialogRef.close();
  }

  reject(){
  }
}

export interface DialogData{
    date : String;
    hall_id : String;
}

export interface SecondDialogData{
  appointment_id : String;
  hall_id : String;
  clinic_admin_id : String;
  type : number;
}

export interface hallModel
{
    name: string | RegExp;
    number: Number | RegExp;
}

export interface deleteHall{
    hall_id : number;
}

export interface editHall {
  name: string;
  number : Number;
  id : number;
}

export interface reserveModel {
  hall_id : string;
  appointment_id : string;
  date : string;
  clinic_admin_id : string;
}
