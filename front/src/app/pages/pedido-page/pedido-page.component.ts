import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, NgModule, OnInit } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { DxFormModule, DxLoadIndicatorModule, DxSelectBoxModule, DxDataGridModule, DevExtremeModule } from 'devextreme-angular';
import { Pedido } from 'src/app/shared/models/pedido';
import { PedidoService } from '../../shared/services/pedido.service';
import { Cliente } from '../../shared/models/cliente';
import { ClienteService } from '../../shared/services/cliente.service';
import { Filme } from 'src/app/shared/models/filme';
import { FilmeService } from '../../shared/services/filme.service';
import { ItemPedido } from '../../shared/models/item-pedido';
import {FirstKeysToConsoleModule} from "../../shared/core/first-keys-to-console.pipe";
import applyChanges from "devextreme/data/apply_changes";


@Component({
  selector: 'app-pedido-page',
  templateUrl: './pedido-page.component.html',
  styleUrls: ['./pedido-page.component.scss']
})
export class PedidoPageComponent implements OnInit {

  pedidos: Pedido[] = [];

  clientes: Cliente[] = [];

  filmes: Filme[] = [];

  itensPedido: ItemPedido[] = [];


  constructor(private pedidoService: PedidoService,
              private clienteService: ClienteService,
              private filmeService: FilmeService) { }

  ngOnInit(): void {
    this.getClientes();
    this.getFilmes();
    this.getPedidos();
  }

  async getClientes(){
   this.clientes = await this.clienteService.listarCLientes().toPromise();
  }

  async getFilmes(){
    this.filmes = await this.filmeService.listarFilmes().toPromise();
  }

  async getPedidos(){
    this.pedidos = await this.pedidoService.listarPedidos().toPromise();
  }

  async onSavingPedido(event: any){
    for (let change of event.changes) {
      if (change.type == 'insert') {
        let pedidoNovo = await this.pedidoService.adicionarPedido(change.data).toPromise();
        this.pedidos.push(pedidoNovo);
        this.pedidos = applyChanges(this.pedidos, [pedidoNovo], {keyExpr: 'id'});
      } else if (change.type == 'update') {
        change.data = Object.assign(change.key, change.data);
        let clienteAlterado = await this.pedidoService.atualizarPedido(change.data).toPromise();
        this.clientes = applyChanges(this.clientes, [clienteAlterado], {keyExpr: 'id'});
      } else if (change.type == 'remove') {
        await this.pedidoService.removerPedido(change.key).toPromise();
      }
    }
  }

  async editarPedido(e: any){

  }

  async removerPedido(e: any){
   this.pedidos =  await this.pedidoService.removerPedido(e.key.id).toPromise();
   this.getPedidos();
  }

  getDisplayCliente(cliente: Cliente){
    if (cliente) {
      return cliente.codigoDoCliente + ' - ' + cliente.nome;
    }
    return cliente;
  }

  getDisplayFilme(filme: Filme){
    if (filme) {
      return filme.codigoDoFilme + ' - ' + filme.nomeDoFilme + ' - ' + filme.tipo;
    }
    return filme;
  }

  adicionarLinhaNoGrid(e: any){
    let item = e.changes[0];
    if(item && item.type=='insert'){
      item.data.valorTotal = item.data.quantidade*item.data.filme.valorUnitarioDoFilme;
    }
    else if(item && item.type=='update' && item.data.quantidade){
      item.data.valorTotal = item.data.quantidade*item.key.filme.valorUnitarioDoFilme;
    }
  }

  valueChangeFilme(event: any, data: any){
    data.data.filme = event;
    event = new Filme();
    console.log(event);
  }

  valueChangeCliente(event: any, data:any){
    data.data.cliente = this.clientes.find(x => x.id==event);

  }

  onInitNewRowItemPedido(event: any){
    if(!event.data.itensDoPedido){
      event.data.itensDoPedido = new Array<ItemPedido>();
    }
  }

}
@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule,
    DxFormModule,
    DxLoadIndicatorModule,
    DxSelectBoxModule,
    DxDataGridModule,
    DevExtremeModule,
    HttpClientModule,
    FirstKeysToConsoleModule,
  ],
  declarations: [PedidoPageComponent],
  exports: [PedidoPageComponent],
})
export class PedidoPageComponentModule {}

/*     let itensDoPedido = new ItemPedido();

itensDoPedido.pedido = e.data.pedido;
itensDoPedido.quantidade =  e.data.quantidade;
itensDoPedido.filme.nomeDoFilme =  e.data.filme.nomeDoFilme;
itensDoPedido.filme.tipo =  e.data.filme.tipo;
itensDoPedido.filme.codigo =  e.data.filme.codigo;
itensDoPedido.filme.valorUnitarioDoFilme =  e.data.filme.valorUnitarioDoFilme;
itensDoPedido.valorTotal =  e.data.filme.valorUnitarioDoFilme * e.data.quantidade;

e.push(this.itensPedido); */




/*     data.data.itemPedido = e;
let item = new ItemPedido();
item.id = e
item.filme = e;
item.pedido = e;
item.quantidade = e;
item.valorTotal = e;

console.log(item);


this.itensPedido.push(item);
*/
//item.quantidade =  e.data.quantidade;
//item.id = e.change.id;
//item.valorTotal = item.filme.valorUnitarioDoFilme * e.change.data.quantidade;
// data.data.valorUnitarioDoFilme = item.filme.valorUnitarioDoFilme;
//console.log(e);
