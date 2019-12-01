package uni.fmi.st.vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import uni.fmi.st.models.Role;
import uni.fmi.st.repos.RoleRepository;
@SpringView
public class RoleView
			extends Composite implements View   {
	private RoleRepository roleRepo;

	public RoleView(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
		init();
	}

	private void init() {
		final Button createButton = new Button("Създай Роля", l->{
			UI.getCurrent().addWindow(new RoleForm(roleRepo));
		}) ;
		final VerticalLayout mainContent =
					new VerticalLayout(createButton,createRoleGrid());
		setCompositionRoot(mainContent);
		
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
