package uni.fmi.st.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import uni.fmi.st.models.Role;
import uni.fmi.st.models.User;
import uni.fmi.st.repos.RoleRepository;
import uni.fmi.st.repos.UserRepository;

@Theme("apptheme")
@SpringUI(path = "/admin-panel")
@SpringComponent
@UIScope
public class VaadinUI extends UI {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;

	@Override
	protected void init(VaadinRequest request) {

		final Grid<User> userGrid = createUserGrid();
		Label label = new Label("Здравей VAADIN!");
		final VerticalLayout mainContent = 
					new VerticalLayout(	label,
										createRoleGrid(),
										userGrid);
		setContent(mainContent);

	}

	private Grid<User> createUserGrid() {
		final Grid<User> userGrid = new Grid<>();
		userGrid.addColumn(User::getUsername).setCaption("Потребителско име");
		userGrid.addColumn(User::getEmail).setCaption("Email");
		userGrid.setItems(userRepo.findAll());
		userGrid.setCaption("Потребителски профили");
		return userGrid;
	}
	private Grid<Role> createRoleGrid() {
		final Grid<Role> result = new Grid<>();
		result.addColumn(Role::getCode).setCaption("Код");
		result.addColumn(Role::getDescription).setCaption("Описание");
		result.setItems(roleRepo.findAll());
		result.setCaption("Роли");
		return result;
	}

}
