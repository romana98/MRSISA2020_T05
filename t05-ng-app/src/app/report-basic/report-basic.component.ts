import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-report-basic',
  templateUrl: './report-basic.component.html',
  styleUrls: ['./report-basic.component.css']
})
export class ReportBasicComponent implements OnInit {

  displayedColumns = ['email', 'name', 'surname', 'avg_rate']

  reportRes : any = null; //name i avg rate klinike
  doctors = []; // lekari, imaju name surname email i avg rate
  incomeClinic : number = -1; //income za kliniku


  sendModel : ReportModel = {
    from: '',
    to: ''
  }

  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private _snackBar: MatSnackBar, private router: ActivatedRoute, private http: HttpClient,
              public dialog: MatDialog, private r:Router) {
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8081/clinicAdministrator/getClinicAvgRate").subscribe(
      res =>{
        console.log(res);
        // @ts-ignore
        this.reportRes = res;
      }
    )
    this.http.get("http://localhost:8081/clinicAdministrator/getDoctorInfo").subscribe(
      res=>{
        console.log(res);
        // @ts-ignore
        this.doctors = res;
        // @ts-ignore
        this.dataSource.data = res;
      }
    )
  }

  report() {
    this.http.post("http://localhost:8081/clinicAdministrator/getIncome", this.sendModel).subscribe(
      res => {
        console.log(res);
        // @ts-ignore
        this.incomeClinic = res;
      }
    )
  }

}

export interface ReportModel{
  from: string,
  to: string
}
