import {TipoDeMidiaEnum} from "./tipo-de-midia.enum";

export class Filme{
  id!: number ;
  codigoDoFilme!: string;
  nomeDoFilme!: string;
  tipo!: TipoDeMidiaEnum;
  valorUnitarioDoFilme!: number;
}
