import {Component, OnInit, ViewChild, ChangeDetectorRef} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
    selector: 'app-add-hall-form',
    templateUrl: './add-hall-form.component.html',
    styleUrls: ['./add-hall-form.component.css']
})
export class AddHallFormComponent implements OnInit{

    displayedColumns: string[] = ['name', 'number', 'delete'];

    dataSource = new MatTableDataSource();

    selectedRowIndex: number = 0;

    model: hallModel = {
        name: '',
        number: 0
    }

    clinic_id : any = '';

    deleteModel : deleteHall = {
        hall_id : 0
    }

    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

    constructor(private _snackBar: MatSnackBar, private http: HttpClient,private changeDetectorRefs: ChangeDetectorRef){

    }


    ngOnInit(): void{
      //vrednost parametra clinic_id treba da se dinamicki popuni tako da se preuzimaju hale tacno odredjene klinike.

      let params1 = new HttpParams().set('admin_id',sessionStorage.getItem('user_id'))

      this.http.get("/clinicAdministrator/getAdminsClinic",{params:params1}).subscribe(
        res => {
          this.clinic_id = res.toString();
          let params = new HttpParams().set('clinic_id', this.clinic_id);
          this.http.get("/halls/getClinicHall",{params:params})
          .subscribe((res) => {
          // @ts-ignore
             this.dataSource.data = res;

          });
        });

        let params = new HttpParams().set('clinic_id', "1");
        this.http.get("/halls/getClinicHall",{params:params})
        .subscribe((res) => {
        // @ts-ignore
           this.dataSource.data = res;
      });

        this.dataSource.paginator = this.paginator;
    }

    AddHall(): void{
        let url = "/halls/addHall"
        this.http.post(url, this.model).subscribe(
            res => {

              //kada dobijem odgovor da sam uspeo da dodam salu hocu da posaljem upit za uzimanje svih sala da bih u tabeli prikazao
              let params = new HttpParams().set('clinic_id', this.clinic_id);
              this.http.get("/halls/getClinicHall",{params:params})
              .subscribe((res) => {
              // @ts-ignore
              this.dataSource.data = res;

              });

              this._snackBar.open("Hall added successfully!", "Close", {
                duration: 2000,
              });

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
        this.http.delete("/halls/deleteHall",{params:params}).subscribe(
          res =>{
            console.log("inside the res");
            let index = this.dataSource.data.indexOf(element);
            this.dataSource.data.splice(index,1);
            this.dataSource._updateChangeSubscription();
            this._snackBar.open("Hall deleted successfully.", "Close", {
            duration: 2000,
            });
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
