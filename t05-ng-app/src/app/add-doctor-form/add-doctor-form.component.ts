import { Component, OnInit } from '@angular/core';

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

  constructor() { }

  ngOnInit(): void {
  }

  addDoctor(): void{
    alert("Doctor is added");
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