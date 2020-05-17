import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatTableDataSource} from "@angular/material/table";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";


@Component({
  selector: 'view-patients-nurse',
  templateUrl: './view-patients-nurse.component.html',
  styleUrls: ['./view-patients-nurse.component.css']
})
export class ViewPatientsNurseComponent implements OnInit {
  displayedColumns: string[] = ['name', 'surname','insurance_number'];

  dataSource = new MatTableDataSource();

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient) {
  }

  model : searchModel = {
    parameter : 'name',
    value : '',
    admin_id : sessionStorage.getItem('id')
  }

  ngOnInit(): void {

    this.model.parameter = 'name'

    
    this.http.get("http://localhost:8081/medicalStaff/getPatients")
      .subscribe((res) => {
        // @ts-ignore
        this.dataSource.data = res;
      });

    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;

  }

  search(): void {
    console.log(sessionStorage.getItem('user_id'))
    let params = new HttpParams();
    params = params.append('parameter', this.model.parameter );
    params = params.append('value',this.model.value);
    params = params.append('admin_id' , sessionStorage.getItem('user_id').toString());
    this.http.get("http://localhost:8081/medicalStaff/searchPatients", {params:params})
      .subscribe((res) => {
        // @ts-ignore
        this.dataSource.data = res;
        document.getElementById("errorMsg").style.display = "none";
    },
    err => {
        this.http.get("http://localhost:8081/medicalStaff/getPatients")
        .subscribe((res) => {
          // @ts-ignore
          this.dataSource.data = res;
        });
        document.getElementById("errorMsg").style.display = "block";
        this.model.value = "";
    });

  }


}

export interface searchModel{
  parameter : string;
  value : string;
  admin_id : string;
}

