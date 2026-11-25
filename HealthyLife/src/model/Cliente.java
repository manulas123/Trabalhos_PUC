package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Notificavel {

    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private List<String> notificacoes;

    public Cliente(int id, String nome, String cpf, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.notificacoes = new ArrayList<>();
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        notificacoes.add(mensagem);
        System.out.println("  [NOTIFICAÇÃO para " + nome + "]: " + mensagem);
    }

    public List<String> getNotificacoes() { return notificacoes; }
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | CPF: %s | Email: %s", id, nome, cpf, email);
    }
}
