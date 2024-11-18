export class Cliente{
    id?: number = 0;
    nome: string= '';
    sobrenome = '';
    cpf: string = '';
    telefone: string = '';
    endereco: string = '';
    email: string = '';
    senha: string = '';
    confirmarSenha = '';
}

export class Usuario extends Cliente{
    
}
