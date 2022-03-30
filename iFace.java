import java.util.Scanner;

public class iFace {
    public static void createUser(User[] user, int user_id, String email, String password, String username) {
        user[user_id] = new User();
        user[user_id].setEmail(email);
        user[user_id].setPassword(password);
        user[user_id].setUsername(username);

        System.out.println("Novo usuário cadastrado com as informações: ");
        System.out.println("E-mail: " + user[user_id].getEmail());
        System.out.println("Senha: " + user[user_id].getPassword());
        System.out.println("Nome de usuário: " + user[user_id].getUsername());
    }

    public static void deleteUser(User[] user, String email, String password) {
        for(int i = 0; i < 100; i++) {
            if(user[i] != null) {
                if((email.equals(user[i].getEmail())) && (password.equals(user[i].getPassword()))) {
                    System.out.println("Found");
                }
            }
        }
    }

    public static void main(String[] args) {
        User[] user = new User[100];
        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Bem-vindo ao iFace. Escolha uma opção: ");

        int user_id = 0;

        while(true) {
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Deletar usuário");
            System.out.println("3 - Terminar programa");
            int input = sc.nextInt();
            System.out.println(input);
            if(input == 1) {
                System.out.println("Opção selecionada: Cadastrar usuário");
                System.out.println("E-mail: ");
                String email = sc.next();
                System.out.println("Senha: ");
                String password = sc.next();               
                System.out.println("Nome de usuário: ");
                String username = sc.next();
                createUser(user, user_id, email, password, username);
                user_id++;
            } else if(input == 2) {
                System.out.println("Opção selecionada: Deletar usuário");
                System.out.println("Digite o e-mail: ");
                String email = sc.next();
                System.out.println("Senha: ");
                String password = sc.next(); 
                deleteUser(user, email, password);
            } else if (input == 3) {
                break;
            }
        }

        sc.close();
    }
}