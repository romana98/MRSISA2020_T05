import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-doctor-form',
  templateUrl: './add-doctor-form.component.html',
  styleUrls: ['./add-doctor-form.component.css']
})
export class AddDoctorFormComponent implements OnInit {

  model: doctorModel = {
    name : '',
    surname : '',
    username : '',
    password : '',
    work_end : '',
    work_start : '',
    type : ''
  }

  constructor(private http: HttpClient) { 

  }

  ngOnInit(): void {
  }

  addDoctor(): void{
    let url =  "http://localhost:8080/doctors/addDoctor"
    this.http.post(url,this.model).subscribe(
        res => {
          alert("Doctor added successfully");
          location.reload();
        },
        err => {
          alert("Error has occured while adding doctor");
          console.log(err)
        }
    );
  }

  checkTime(): boolean{
    //true -- time is bad
    //false -- time is good
    let hours = 0;
    let minutes = 0;
      try{
        let all = this.model.work_start.split(":");
        let hours = parseInt(all[0]);
        let minutes = parseInt(all[1]);
      }
      catch{
        return true;
      }
      
      if(hours > 0 && hours < 24 && minutes > 0 && minutes < 60){
           return false;
      }
          return true;
  }

}

export interface doctorModel
{
    name: string;
    surname: string;
    username : string;
    password : string; 
    work_start : string;
    work_end : string;
    type : string;

}