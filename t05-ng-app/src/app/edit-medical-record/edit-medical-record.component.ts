import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-edit-medical-record',
  templateUrl: './edit-medical-record.component.html',
  styleUrls: ['./edit-medical-record.component.css']
})
export class EditMedicalRecordComponent implements OnInit {

  displayedColumns: string[] = ['date', 'time', 'duration', 'price', 'doctor', 'hall', 'appointmentType'];

  displayedColumns1: string[] = ['name', 'description', 'date'];

  dataSource = new MatTableDataSource();

  dataSource1 = new MatTableDataSource();

  selected:string = "";

  modelDis: any[];

  isDisabled:boolean = true;

  selectedRowId:number;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatPaginator, {static: true}) paginator1: MatPaginator;
  isHidden:boolean = sessionStorage.getItem('role')===('ROLE_NURSE');

  constructor(private _snackBar: MatSnackBar,private router: ActivatedRoute, private http: HttpClient) { }


  ngOnInit(): void {

    let url = "https://eclinic05.herokuapp.com/patients/getDisease";
    let url1 = "https://eclinic05.herokuapp.com/appointment/getFinishedAppointmentsMed";

    this.http.get(url, {params:{email: this.router.snapshot.queryParamMap.get('email')}}).subscribe(
      res => {
        // @ts-ignore
        this.modelDis = res;
      }
    )

    this.http.get(url1, {params:{email: this.router.snapshot.queryParamMap.get('email')}}).subscribe(
      res => {
        // @ts-ignore
        this.dataSource.data = res;
        console.log(this.dataSource.data)
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
    this.isDisabled = sessionStorage.getItem('user_id')!=row.doctor.id;
    this.selected = row.description;
    this.selectedRowId = this.dataSource.data.indexOf(row);
  }

  save() {

    let url = "https://eclinic05.herokuapp.com/appointment/saveDescription";
    // @ts-ignore
    this.http.get(url, {params:{appId: this.dataSource.data[this.selectedRowId].id, desc: this.selected}}).subscribe(
      res => {
        this._snackBar.open("Description successfully saved!", "Close", {
          duration: 2000,
        });
        // @ts-ignore
        this.dataSource.data[this.selectedRowId].description = this.selected;
      },
      err =>{
        this._snackBar.open("Error occurred while saving description!", "Close", {
          duration: 2000,
        });
      }
    )
  }
}
