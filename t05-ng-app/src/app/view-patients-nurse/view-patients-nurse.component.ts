import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
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
  displayedColumns: string[] = ['email', 'name', 'surname', 'address',
    'city', 'country', 'phone_number', 'insurance_number'];

  dataSource = new MatTableDataSource();

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient) {
  }

  ngOnInit(): void {


    this.http.get("http://localhost:8081/nurse/getPatients")
      .subscribe((res) => {
        // @ts-ignore
        this.dataSource.data = res;
      });

    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;

  }



}

