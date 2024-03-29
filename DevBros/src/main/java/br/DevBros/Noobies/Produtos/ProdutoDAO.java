package br.DevBros.Noobies.Produtos;

import br.DevBros.Noobies.Utils.ConnectionUtils;
import static br.DevBros.Noobies.Utils.ConnectionUtils.obterConexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static boolean incluirProduto(Produto produto) {

        //Abrir conexao e deixa ela null
        PreparedStatement stmt = null;
        Connection conn = null;

        boolean linhasAfetadas = false;

        //Preparar string sql
        String sql = "INSERT INTO tb_produtos (NOME_PRODUTO, DESCRICAO, VALOR_COMPRA, "
                + "VALOR_VENDA, QUANTIDADE, CATEGORIA)"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        //Obtem conexão para SQL workbench
        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNomeProd());
            stmt.setString(2, produto.getDescProd());
            stmt.setDouble(3, produto.getValorCompra());
            stmt.setDouble(4, produto.getValorVenda());
            stmt.setInt(5, produto.getQuantidade());
            stmt.setString(6, produto.getCategoria());

            // 2) Executar SQL
            stmt.executeUpdate();
        } catch (SQLException ex) {
            linhasAfetadas = false;
            System.out.println("Não foi possível executar. SQL Exception");
        } catch (ClassNotFoundException ex) {
            linhasAfetadas = false;
            System.out.println("Não foi possível executar. ClassNotFound EXception");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    linhasAfetadas = false;
                    System.out.println("Erro ao fechar stmt.");
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    linhasAfetadas = true;
                } catch (SQLException ex) {
                    linhasAfetadas = false;
                    System.out.println("Erro ao fechar conn.");
                }
            }
        }
        return linhasAfetadas;
    }

    public static boolean excluirProduto(Produto produto) {
        PreparedStatement stmt = null;
        Connection conn = null;
        boolean verdade = false;
        //Preparar string sql
        String sql = "DELETE FROM TB_PRODUTOS WHERE COD_PRODUTO = ?";//INSERIR VARIÁVEL COM COD_PRODUTO

        //Obtem conexão para SQL workbench
        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, produto.getCodProduto());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Não foi possível executar. SQL Exception");
            verdade = false;
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possível executar. ClassNotFound EXception");
            verdade = false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    verdade = true;
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar stmt.");
                    verdade = false;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    verdade = true;
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conn.");
                    verdade = false;
                }
            }
        }
        return verdade;
    }

    public static void editarProduto(Produto produto) {
        //Abrir conexao e deixa ela null
        PreparedStatement stmt = null;
        Connection conn = null;

        //Preparar string sql
        String sql = "UPDATE tb_produtos SET NOME_PRODUTO = '" + produto.getNomeProd() + "', VALOR_COMPRA = " + produto.getValorCompra() + ", VALOR_VENDA = " + produto.getValorVenda() + ""
                + ", QUANTIDADE = " + produto.getQuantidade() + ", CATEGORIA = '" + produto.getCategoria() + "' WHERE COD_PRODUTO = " + produto.getCodProduto() + ";";

        //Obten conexão para SQL workbench
        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);

            // 2) Executar SQL
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Não foi possível executar. SQL Exception");
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possível executar. ClassNotFound EXception");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar stmt.");
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conn.");
                }
            }
        }
    }

    public static List<Produto> consultarProduto(String pesquisa) {
        List<Produto> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "SELECT * FROM tb_produtos WHERE NOME_PRODUTO LIKE '%" + pesquisa + "%';";

        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setCodProduto(rs.getInt("COD_PRODUTO"));
                prod.setNomeProd(rs.getString("NOME_PRODUTO"));
                prod.setValorCompra(rs.getDouble("VALOR_COMPRA"));
                prod.setValorVenda(rs.getDouble("VALOR_VENDA"));
                prod.setQuantidade(rs.getInt("QUANTIDADE"));
                prod.setCategoria(rs.getString("CATEGORIA"));

                lista.add(prod);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não foi possível executar" + e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão" + e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão" + e);
                }
            }
        }

        return lista;
    }

    public static List<Produto> listarProdutos() {
        List<Produto> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "SELECT * FROM tb_produtos LIMIT 8";

        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setCodProduto(rs.getInt("COD_PRODUTO"));
                prod.setNomeProd(rs.getString("NOME_PRODUTO"));
                prod.setValorCompra(rs.getDouble("VALOR_COMPRA"));
                prod.setValorVenda(rs.getDouble("VALOR_VENDA"));
                prod.setQuantidade(rs.getInt("QUANTIDADE"));
                prod.setCategoria(rs.getString("CATEGORIA"));

                lista.add(prod);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não foi possível executar" + e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão" + e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão" + e);
                }
            }
        }

        return lista;
    }

    public static List<Produto> procurar(String nomeProduto)
            throws SQLException, Exception {

        String sql = " SELECT ID, NOME_PRODUTO, DESCRICAO, VALOR_COMPRA, "
                + "VALOR_VENDA, QUANTIDADE, CATEGORIA from tb_produtos "
                + "WHERE NOME_PRODUTO LIKE (?)";

        List<Produto> listaProdutos = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            connection = ConnectionUtils.obterConexao();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, "%" + nomeProduto + "%");

            result = preparedStatement.executeQuery();

            while (result.next()) {
                if (listaProdutos == null) {
                    listaProdutos = new ArrayList<>();
                }
                Produto produto = new Produto();
                produto.setCodProduto(result.getInt("COD_PRODUTO"));
                produto.setNomeProd(result.getString("NOME_PRODUTO"));
                produto.setValorCompra(result.getDouble("VALOR_COMPRA"));
                produto.setValorVenda(result.getDouble("VALOR_VENDA"));
                produto.setQuantidade(result.getInt("QUANTIDADE"));
                produto.setCategoria(result.getString("CATEGORIA"));
                listaProdutos.add(produto);
            }
        } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return listaProdutos;
    }

    public static List<Produto> pesquisarProduto(int pesquisa) {
        List<Produto> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "SELECT * FROM tb_produtos WHERE NOME_PRODUTO LIKE '%" + pesquisa + "%';";

        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setCodProduto(rs.getInt("COD_PRODUTO"));
                prod.setNomeProd(rs.getString("NOME_PRODUTO"));
                prod.setValorCompra(rs.getDouble("VALOR_COMPRA"));
                prod.setValorVenda(rs.getDouble("VALOR_VENDA"));
                prod.setQuantidade(rs.getInt("QUANTIDADE"));
                prod.setCategoria(rs.getString("CATEGORIA"));

                lista.add(prod);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não foi possível executar" + e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão" + e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão" + e);
                }
            }
        }

        return lista;
    }
    
    public static Produto pesquisar(Produto p){
        PreparedStatement stmt = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM tb_produtos WHERE COD_PRODUTO=?";
        
        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, p.getCodProduto());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                p.setValorVenda(rs.getDouble("VALOR_VENDA"));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não foi possível executar" + e);
        } finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão" + e);
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão" + e);
                }
            }
        }
        return p;
    }
}
