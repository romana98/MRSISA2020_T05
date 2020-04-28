import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'app-add-clinic-form',
    templateUrl: './add-clinic-form.component.html',
    styleUrls: ['./add-clinic-form.component.css']
})
export class AddClinicFromComponent implements OnInit{

    model: clinicModel = {
        name: '',
        address: '',
        description: ''
    }

    ngOnInit(): void{

    }


    constructor(private http: HttpClient){

    }

    AddClinic(): void{
        let url = "http://localhost:8081/clinic/addClinic"
        this.http.post(url, this.model).subscribe(
            res => {
                alert("Clinic added successfully");

            },
            err => {
              if(err.status == 409)
              {
                alert("Clinic already exists");
              }
              else
              {
                alert("Error has occurred while adding clinic");
                console.log(err);
              }
            }
        );

    }
}

export interface clinicModel
{
    name: string | RegExp;
    address: string | RegExp;
    description: string;
}
