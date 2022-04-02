import { RouterModule } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminPanelComponent } from './adminPanel/adminPanel.component';


let routing = RouterModule.forChild([
  { path: 'auth', component: AuthComponent },
  { path: 'main', component: AdminPanelComponent },
  { path: '**', redirectTo: 'auth' }
]);

@NgModule({
  imports: [CommonModule, routing],
  declarations: [AuthComponent, AdminPanelComponent]
})
export class AdminModule {
}
