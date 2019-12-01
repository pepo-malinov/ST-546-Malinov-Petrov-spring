package uni.fmi.st.vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import uni.fmi.st.models.Role;
import uni.fmi.st.models.User;
import uni.fmi.st.repos.UserRepository;

@SpringView
public class UserDetailsView 
			extends Composite implements View  {

	private static final long serialVersionUID = 1L;

	private UserRepository userRepo;
	private Grid<Role> roleGrid;

	public UserDetailsView(UserRepository userRepo) {
		this.userRepo = userRepo;
		init();
	}

	private void init() {
		
		final VerticalLayout mainContent = 
				new VerticalLayout(createUserGrid(), createRoleGrid());
		setCompositionRoot(mainContent);
		setCaption("Информация за потребителски профили");
	}
	
	private Grid<User> createUserGrid() {
		final Grid<User> userGrid = new Grid<>();
		userGrid.addColumn(User::getUsername).setCaption("Потребителско име");
		userGrid.addColumn(User::getEmail).setCaption("Email");
		userGrid.setItems(userRepo.findAll());
		userGrid.setCaption("Потребителски профили");
		userGrid.addSelectionListener(l->{
			User user = l.getFirstSelectedItem().get();
			roleGrid.setItems(user.getRoles());
		});
		return userGrid;
	}
	private Grid<Role> createRoleGrid() {
		roleGrid = new Grid<>();
		roleGrid.addColumn(Role::getCode).setCaption("Код");
		roleGrid.addColumn(Role::getDescription).setCaption("Описание");
		roleGrid.setCaption("Роли селектиран потребител");
		return roleGrid;
	}

}
