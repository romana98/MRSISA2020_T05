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
    doctors:any = [],
    clinicAverageRate: null,
    from: null,
    to: null,
    income: null
  }

  modelReceived: ReportModel = {
    clinic:null,
    doctors:any = [],
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
  }

  report() {
    this.http.post("http://localhost:8081/clinicAdministrator/getClinicReport", this.model).subscribe(
      res => {
        this.dataSource.data = res.doctors;
        this.model.clinic = res.clinic;
        this.model.doctors = res.doctors;
        this.model.clinicAverageRate = res.clinicAverageRate;
        this.model.from = res.from;
        this.model.to = res.to;
        this.model.income = res.income;
      }
    )
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
