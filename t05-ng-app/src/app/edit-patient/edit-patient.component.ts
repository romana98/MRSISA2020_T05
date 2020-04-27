import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-edit-patient',
    templateUrl: './edit-patient.component.html',
    styleUrls: ['./edit-patient.component.css']
})
export class EditPatientProfile implements OnInit{

    model: patientModel = {
        email : 'email1@email.com',
        password : 'password1',
        name : 'Ime1',
        surname : 'Prezime1',
        address : 'Adresa1',
        city : 'Grad1',
        country : 'Drzava1',
        phone_number : 'Telefon1',
        insurance_number : 'Jedinstveni1'
    }

    constructor(private http: HttpClient){

    }

    ngOnInit(): void{

    }

    editPatient(): void{
        let url = "http://localhost:8081/patients/editPatient"
        this.http.post(url,this.model).subscribe(
            res => {
                alert("Your profile has been updated successfully!");
                location.reload();
            },
            err => {
                alert("Error has occurred while updating your profile!");
                console.log(err)
            }
        );
    }
}
    export interface patientModel{
        email : string;
        password : string;
        name : string;
        surname : string;
        address : string;
        city : string;
        country : string;
        phone_number : string;
        insurance_number : string;
    }

