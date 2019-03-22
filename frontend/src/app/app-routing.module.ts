import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {GameComponent} from './game/game.component';

const routes: Routes = [
  {path: 'game', component: GameComponent, runGuardsAndResolvers: 'always'},

  {path: '', redirectTo: '/game', pathMatch: 'full', runGuardsAndResolvers: 'always' },
  {path: '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
