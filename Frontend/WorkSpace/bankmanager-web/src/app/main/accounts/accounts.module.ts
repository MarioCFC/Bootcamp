import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { AccountsRoutingModule } from './accounts-routing.module';
import { AccountsHomeComponent } from './accounts-home/accounts-home.component';
import { AccountNumberRenderComponent } from './accounts-home/account-number-render/account-number-render.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [AccountsHomeComponent, AccountNumberRenderComponent],
  imports: [
    CommonModule,
    SharedModule,
    OntimizeWebModule,
    AccountsRoutingModule
  ]
})
export class AccountsModule { }
