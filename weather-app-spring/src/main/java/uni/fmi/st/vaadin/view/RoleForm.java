package uni.fmi.st.vaadin.view;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import uni.fmi.st.models.Role;
import uni.fmi.st.repos.RoleRepository;

public class RoleForm extends Window {

	private RoleRepository roleRepo;
	public RoleForm( RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
		init();
	}
	private void init() {
		setModal(true);
		center();
		
		setHeight(500, Unit.PIXELS);
		setWidth(500, Unit.PIXELS);
		
		final TextField code = new TextField("Код");
		final TextField descr = new TextField("Описание");
		
		Button save = new Button("Съхрани",l ->{
			final Role role = new Role();
			role.setCode(code.getValue());
			role.setDescription(descr.getValue());
			roleRepo.save(role);
			close();
		}) ;
		Button cancel = new Button("Откажи", l->close());
		final HorizontalLayout buttons = new HorizontalLayout(save, cancel);
		
		final FormLayout form = new FormLayout(code, descr);
		
		VerticalLayout content = new VerticalLayout(form, buttons);
		content.setExpandRatio(form, 1.f);
		content.setSpacing(true);
		content.setMargin(true);
		content.setSizeFull();
		content.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
		setContent(content);
		
	}
}
