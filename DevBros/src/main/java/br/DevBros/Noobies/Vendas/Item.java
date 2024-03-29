package br.DevBros.Noobies.Vendas;

import br.DevBros.Noobies.Produtos.Produto;

public class Item {

    
    private int idItem;
    private Produto produto = new Produto();
    private int quantidade;
    private double valorTotal = 0.0;
    
    public Item(){
    }
    
    public Item(Produto prod, int quant) {    
        this.produto.setCodProduto(prod.getCodProduto());
        this.produto.setNomeProd(prod.getNomeProd());
        this.produto.setCategoria(prod.getCategoria());
        this.produto.setValorVenda(prod.getValorVenda());
        this.quantidade = quant;
    }
    
    public Item(int idItem,Produto prod, int quant) {    
        this.produto.setCodProduto(prod.getCodProduto());
        this.produto.setNomeProd(prod.getNomeProd());
        this.produto.setCategoria(prod.getCategoria());
        this.produto.setValorVenda(prod.getValorVenda());
        this.quantidade = quant;
        this.setIdItem(idItem++);
    }
    
    public Produto getProduto() {
        return produto;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public double getConta(Produto prod, int quantidade){
        valorTotal += prod.getValorVenda() * quantidade;
        return valorTotal;
    }
    
    public double getContaTotal(){
        return this.getConta(produto, quantidade);
    }
}
