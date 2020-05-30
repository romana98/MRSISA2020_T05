import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpParams, HttpClient } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {

  displayedColumns: string[] = ['name', 'surname', 'avg_rating', 'times'];

  dataSource = new MatTableDataSource();

  selectedRowIndex: number = 0;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  constructor(private router: ActivatedRoute, private http: HttpClient) { }

  label : String = '';

  model : doctorModel = {
    name : '',
    surname : '',
    average_rate : 0,
    available_times : null
  }

  ngOnInit(): void {
    this.label = this.router.snapshot.queryParamMap.get('clinic_id');
    this.label = this.router.snapshot.queryParamMap.get('date');
    let params = new HttpParams();
    params = params.append('date', this.router.snapshot.queryParamMap.get('date'))
    params = params.append('clinic_id', this.router.snapshot.queryParamMap.get('clinic_id'));
    params = params.append('appointmentType_id', this.router.snapshot.queryParamMap.get('appointment_type_id'));
    this.http.get("http://localhost:8081/doctors/getAvailableDoctors",{params:params})
    .subscribe((res) => {
      // @ts-ignore
      this.dataSource.data = res;

    });

  }

}


export interface doctorModel{
  name: string,
  surname: string,
  average_rate : number,
  available_times : string[]
}