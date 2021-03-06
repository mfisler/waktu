package ch.hsr.waktu.presentation.view.usermanagment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import ch.hsr.waktu.domain.User;
import ch.hsr.waktu.domain.UserProperties;
import ch.hsr.waktu.presentation.model.UserTreeModel;

public class UserManagmentView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988600126721632829L;
	
	private JTree userTree;
	private JSplitPane splitPane;

	/**
	 * Create the frame.
	 */
	public UserManagmentView() {
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gbl_contentPane);
		
		splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		add(splitPane, gbc_splitPane);
		
		JPanel pnTree = new JPanel();
		splitPane.setLeftComponent(pnTree);
		GridBagLayout gbl_pnTree = new GridBagLayout();
		gbl_pnTree.columnWidths = new int[]{32, 0};
		gbl_pnTree.rowHeights = new int[]{53, 0};
		gbl_pnTree.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnTree.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnTree.setLayout(gbl_pnTree);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnTree.add(scrollPane, gbc_scrollPane);
		
		UserTreeModel model = new UserTreeModel();
		userTree = new JTree(model);
		scrollPane.setViewportView(userTree);
		
		userTree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				 TreePath path = userTree.getSelectionPath();
				    if (path == null)
				      return;
				    Object selectedNode = path.getLastPathComponent();
				    TreePath parentPath = path.getParentPath();
				    if (selectedNode instanceof UserProperties) {
				    	UserProperties userProperties = (UserProperties) selectedNode;
				    	if (userProperties.getDescription().equals("Daten")) {
				    		splitPane.setRightComponent(new UserDetailView((User)parentPath.getLastPathComponent()));
				    	}
				    	else {
				    		splitPane.setRightComponent(new JPanel());
				    	}
				    }
				    
			}
		});
		
		JPanel pnDetails = new JPanel();
		splitPane.setRightComponent(pnDetails);
		GridBagLayout gbl_pnDetails = new GridBagLayout();
		gbl_pnDetails.columnWidths = new int[]{0};
		gbl_pnDetails.rowHeights = new int[]{0};
		gbl_pnDetails.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_pnDetails.rowWeights = new double[]{Double.MIN_VALUE};
		pnDetails.setLayout(gbl_pnDetails);
	}

}
