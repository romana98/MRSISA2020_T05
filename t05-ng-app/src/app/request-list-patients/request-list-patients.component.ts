import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'request-list-patients',
  templateUrl: './request-list-patients.component.html',
  styleUrls: ['./request-list-patients.component.css']
})
export class RequestListPatientsComponent implements OnInit{

  requests: any=[];

  constructor(private http: HttpClient){

  }

  ngOnInit(): void{
    this.http.get("http://localhost:8081/registrationRequests/getRequests")
      .subscribe((res)=>{
        this.requests = res;
      });

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
