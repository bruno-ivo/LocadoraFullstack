import { Filme } from './filme';
import {Pedido} from "./pedido";

export class ItemPedido {
  id!: number ;
  pedido!: Pedido;
  filme!: Filme;
  quantidade!: number;
  valorTotal!: number;
}
