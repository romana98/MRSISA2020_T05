import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Router} from "@angular/router";

@Component({
  selector: 'app-add-medicine-form',
  templateUrl: './add-medicine-form.component.html',
  styleUrls: ['./add-medicine-form.component.css']
})
export class AddMedicineFromComponent implements OnInit{

  model: medicineModel = {
    name: '',
    description: '',
    authenticated: false
  }
  buttonType: string;

  ngOnInit(): void{

  }

  constructor(private http: HttpClient, private router: Router){

  }

  AddMedicine(): void{
    let url = "http://localhost:8081/medicine/addMedicine"
    this.http.post(url, this.model).subscribe(
      res => {
        alert("Medicine added successfully");

      },
      err => {
        alert("Error has occurred while adding medicine");
        console.log(err);
      }
    );

  }

  onSubmit(buttonType): void {

    if(buttonType==="Add"){
      this.AddMedicine();
    }
    if(buttonType==="Leave"){
      this.AddMedicine();
      this.router.navigateByUrl('/');
    }

  }
}

export interface medicineModel
{
  name: string | RegExp;
  description: string;
  authenticated: boolean;
}
