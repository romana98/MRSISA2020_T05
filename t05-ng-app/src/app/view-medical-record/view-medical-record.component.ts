import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-view-medical-record',
  templateUrl: './view-medical-record.component.html',
  styleUrls: ['./view-medical-record.component.css']
})
export class ViewMedicalRecordComponent implements OnInit {

  displayedColumns: string[] = ['date', 'time', 'duration', 'price', 'doctor', 'hall', 'appointmentType'];

  displayedColumns1: string[] = ['name', 'description', 'date'];

  dataSource = new MatTableDataSource();

  dataSource1 = new MatTableDataSource();

  selected:string = "";

  modelDis: any[];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatPaginator, {static: true}) paginator1: MatPaginator;


  constructor(private _snackBar: MatSnackBar,private router: ActivatedRoute, private http: HttpClient) { }

  ngOnInit(): void {

    let url = "http://localhost:8081/patients/getDiseasePatient";
    let url1 = "http://localhost:8081/appointment/getFinishedAppointments";

    this.http.get(url).subscribe(
      res => {
        // @ts-ignore
        this.modelDis = res;
      }
    )

    this.http.get(url1).subscribe(
      res => {

        // @ts-ignore
        this.dataSource.data = res;

        this.dataSource.data.forEach(data =>{
          // @ts-ignore
          this.dataSource1.data.push(data.diagnosis)
        })
        this.dataSource1._updateChangeSubscription()
      }
    )

    this.dataSource.paginator = this.paginator;
    this.dataSource1.paginator = this.paginator1;
  }

  Select(row: any) {
    this.selected = row.description;
  }
}
