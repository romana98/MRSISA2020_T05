import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";


@Component({
  selector: 'request-list-patients',
  templateUrl: './request-list-patients.component.html',
  styleUrls: ['./request-list-patients.component.css']
})
export class RequestListPatientsComponent implements OnInit{
  displayedColumns: string[] = ['email', 'name', 'surname', 'address',
  'city', 'country', 'phone_number', 'insurance_number'];

  private requests: request[];

  dataSource: any;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private http: HttpClient){

  }

  ngOnInit(): void{


    this.http.get("http://localhost:8081/registrationRequests/getRequests")
      .subscribe((res)=>{
        // @ts-ignore
        this.requests = res;
      });

    this.dataSource = new MatTableDataSource<request>(this.requests);
    this.dataSource.paginator = this.paginator;
    console.log(this.dataSource)
  }

  Accept(req)
  {

    let url = "http://localhost:8081/patients/addPatient"
    this.http.post(url,req).subscribe(
      res => {
        let index = this.requests.indexOf(req);
        this.requests.splice(index, 1);
        alert("Patient registered");
      },
      err => {
        if(err.status == 409)
        {
          alert("Patient already exists with" + req.email + "email");
        }
        else {
          alert("Error has occurred while registering patient");
          console.log(err);
        }
      }
    );
  }

  Decline(req)
  { }


}

export interface request {
  email : string;
  name : string;
  surname : string;
  address : string;
  city : string;
  country : string;
  phone_number : string;
  insurance_number : string;
}
