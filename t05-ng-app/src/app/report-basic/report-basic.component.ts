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

  model: ReportModel = {
    clinic:null,
    doctors: [],
    clinicAverageRate: null,
    from: null,
    to: null,
    income: null
  }

  modelReceived: ReportModel = {
    clinic:null,
    doctors: [],
    clinicAverageRate: null,
    from: null,
    to: null,
    income: null
  }
  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private _snackBar: MatSnackBar, private router: ActivatedRoute, private http: HttpClient,
              public dialog: MatDialog, private r:Router) {
  }

  ngOnInit(): void {
    this.http.post("http://localhost:8081/clinicAdministrator/getClinicReport", this.model).subscribe(
      res => {

        // @ts-ignore
        this.dataSource.data = res.doctors;
        // @ts-ignore
        this.model.clinic = res.clinic;
        // @ts-ignore
        this.model.doctors = res.doctors;
        // @ts-ignore
        this.model.clinicAverageRate = res.clinicAverageRate;
        // @ts-ignore
        this.model.from = res.from;
        // @ts-ignore
        this.model.to = res.to;
        // @ts-ignore
        this.model.income = res.income;

        console.log(res)

      }
    )
  }

  report() {

  }

}

export interface ReportModel{
  clinic:any,
  doctors:any[],
  clinicAverageRate:number,
  from: Date,
  to: Date,
  income: number
}
