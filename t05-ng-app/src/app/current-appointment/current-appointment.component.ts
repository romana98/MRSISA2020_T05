import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {diagnosisModel} from "../add-diagnosis-form/add-diagnosis-form.component";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {patientModel} from "../view-patient-profile/view-patient-profile.component";

@Component({
  selector: 'app-current-appointment',
  templateUrl: './current-appointment.component.html',
  styleUrls: ['./current-appointment.component.css']
})
export class CurrentAppointmentComponent implements OnInit {

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  displayedColumns: string[] = ['name', 'description', 'check'];

  dataSource = new MatTableDataSource();

  insurance:string;
  patient:string;
  email:string;

  diagnosises = [];

  modelApp: appModel = {
    id:'',
    description: '',
    time: ''
  }

  modelDis: any[];

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              private router: ActivatedRoute, private r: Router) { }

  ngOnInit(): void {
    this.insurance =  this.router.snapshot.queryParamMap.get('insurance');
    this.patient =  this.router.snapshot.queryParamMap.get('patient');
    this.modelApp.id =  this.router.snapshot.queryParamMap.get('appId');

    let url = "http://localhost:8081/patients/getDisease";

    this.http.get(url, {params:{email: this.router.snapshot.queryParamMap.get('email')}}).subscribe(
      res => {
        // @ts-ignore
        this.modelDis = res;
        this.modelDis.push({name:this.router.snapshot.queryParamMap.get('email'), value:"", description:null});
      }
    )

    let url1 = "http://localhost:8081/diagnosis/getDiagnosises";

    this.http.get(url1,).subscribe(
      res => {
        // @ts-ignore
        this.diagnosises = res;

      }
    )

    let url2 = "http://localhost:8081/medicine/getMedicines";

    this.http.get(url2,).subscribe(
      res => {
        // @ts-ignore
        this.dataSource.data = res;

        this.dataSource.data.map((obj) => {
          obj['selected'] = false;
          return obj;
        })
      }
    )


  }



  async finishAppointment() {

    let url = "http://localhost:8081/patients/setDisease";
    let url1 = "http://localhost:8081/patients/setAppointment";
    let url2 = "http://localhost:8081/patients/setMedicine";

    //sacuvaj bolesti
    this.http.post(url, this.modelDis).subscribe(
      res => {
        //sacuvaj appointment
        this.http.post(url1, this.modelApp).subscribe(
          res => {

            for (let i = 0; i < this.dataSource.data.length; i++) {
             // @ts-ignore
              console.log(this.dataSource.data[i].selected)
              // @ts-ignore
              console.log(this.dataSource.data[i].selected === false)
              // @ts-ignore
              if(this.dataSource.data[i].selected === false)
              {
                this.dataSource.data.splice(i,1)
              }
              else
              {
                // @ts-ignore
                delete this.dataSource.data[i].selected
              }

            }
            this.dataSource.data.push({name: "", description: "", id: this.modelApp.id});
            console.log(this.dataSource.data)
            //sacuvaj recepte
            this.http.post(url2, this.dataSource.data).subscribe(
              res => {
                this._snackBar.open("Successfully finished appointment!", "Close", {
                  duration: 2000,
                });
                console.log(this.modelApp.id);
                let booleanPromise = this.r.navigate(["../addAnotherAppointment"], { queryParams: { app_id: this.modelApp.id } , relativeTo: this.router });


              },
              err => {
                this._snackBar.open("Error has occurred while finishing appointment!", "Close", {
                  duration: 2000,
                });
                console.log(err);
              }
            );
          },
          err => {
            this._snackBar.open("Error has occurred while finishing appointment!", "Close", {
              duration: 2000,
            });
            console.log(err);
          }
        );

      },
      err => {
        this._snackBar.open("Error has occurred while finishing appointment!", "Close", {
          duration: 2000,
        });
        console.log(err);
      }
    );



  }

}

export interface appModel{
  id:string;
  description:string;
  time:string;

}
