package uni.fmi.st.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import uni.fmi.st.repos.RoleRepository;
import uni.fmi.st.repos.UserRepository;
import uni.fmi.st.vaadin.view.RoleView;
import uni.fmi.st.vaadin.view.UserDetailsView;

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

		final Button userDetailsButton = new Button("Потребители",
				listener ->getNavigator().navigateTo("userDetailsView"));
		userDetailsButton.addStyleNames(ValoTheme.BUTTON_LARGE,
											ValoTheme.MENU_TITLE);
		
		
		final Button roleButton = new Button("Роли",
				listener ->getNavigator().navigateTo("roleView"));
		roleButton.addStyleNames(ValoTheme.BUTTON_LINK,
									ValoTheme.MENU_TITLE);
		final CssLayout menu = 
				new CssLayout(userDetailsButton, roleButton);
		menu.addStyleName(ValoTheme.MENU_ROOT);
		menu.setHeight("100%");
		final CssLayout viewContainer = new CssLayout();
		final HorizontalLayout mainContent = 
				new HorizontalLayout(menu, viewContainer);
		mainContent.setExpandRatio(viewContainer, 1.f);
		setContent(mainContent);
		mainContent.setSizeFull();
		
		final Navigator nav = new Navigator(this, viewContainer);
		nav.addView("", new UserDetailsView(userRepo));
		nav.addView("userDetailsView", new UserDetailsView(userRepo));
		nav.addView("roleView", new RoleView(roleRepo));
	}


}
