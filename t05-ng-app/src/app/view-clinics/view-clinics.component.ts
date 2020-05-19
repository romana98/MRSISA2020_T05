import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";


@Component({
  selector: 'view-clinics',
  templateUrl: './view-clinics.component.html',
  styleUrls: ['./view-clinics.component.css']
})
export class ViewClinicsComponent implements OnInit {
  displayedColumns: string[] = ['name', 'address', 'description'];

  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {


    this.http.get("/clinic/getClinics")
      .subscribe((res) => {
        // @ts-ignore
        this.dataSource.data = res;

      });

    this.dataSource.paginator = this.paginator;

  }

}
