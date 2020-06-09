import {Component, OnInit, ViewChild, ChangeDetectorRef} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { EXPANSION_PANEL_ANIMATION_TIMING, MatExpansionPanel } from '@angular/material/expansion';

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

    search_param : String = '';
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

    constructor(private _snackBar: MatSnackBar, private http: HttpClient,private changeDetectorRefs: ChangeDetectorRef){

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
      console.log(this.search_param)
    }

    goSearch(){
      let date : String;
      date = this.date_value.getDate() + "/" + (this.date_value.getMonth()+1) + "/" + this.date_value.getFullYear();
      this.http.get("http://localhost:8081/halls/getAvailabileHalls",{params:{'clinic_admin_id' : sessionStorage.getItem('user_id'),
                                                                              'param_name': this.search_param.toString(),
                                                                              'param_value' : this.search_value.toString(),
                                                                              'date' : date.toString()}}).subscribe(
        res => {
        //@ts-ignore
        this.dataSource.data = res;
        },err => {
        this._snackBar.open("Could not perform search, check parameter and try again!", "Close", {
          duration: 2000,});
        }
        );

    }

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
