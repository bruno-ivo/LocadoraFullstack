import { Cliente } from 'src/app/shared/models/cliente';
import { ItemPedido } from './item-pedido';

export class Pedido{
  id!: number ;
  cliente!: Cliente;
  dataDeLocacao!: Date;
  dataDeDevolucao!: Date;
  valorTotal!: number;
  itensDoPedido!: ItemPedido[];
}
