import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Router} from "@angular/router";

@Component({
  selector: 'app-add-diagnosis-form',
  templateUrl: './add-diagnosis-form.component.html',
  styleUrls: ['./add-diagnosis-form.component.css']
})
export class AddDiagnosisFormComponent implements OnInit{

  model: diagnosisModel = {
    name: '',
    description: ''
  }
  buttonType: string;

  ngOnInit(): void{

  }

  constructor(private http: HttpClient, private router: Router){

  }

  AddDiagnosis(): void{
    let url = "http://localhost:8081/diagnosis/addDiagnosis"
    this.http.post(url, this.model).subscribe(
      res => {
        alert("Diagnosis added successfully");
        location.reload();
      },
      err => {
        alert("Error has occurred while adding diagnosis");
        console.log(err);
      }
    );

  }

  onSubmit(buttonType): void {
    if(buttonType==="Add"){
      this.AddDiagnosis();
    }
    if(buttonType==="Leave"){
      this.AddDiagnosis();
      this.router.navigateByUrl('/');
    }

  }
}

export interface diagnosisModel
{
  name: string | RegExp;
  description: string;

}
