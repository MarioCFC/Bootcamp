import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BranchesRoutingModule } from './branches-routing.module';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { BranchesHomeComponent } from './branches-home/branches-home.component';


@NgModule({
  declarations: [BranchesHomeComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    BranchesRoutingModule
  ]
})
export class BranchesModule { }
