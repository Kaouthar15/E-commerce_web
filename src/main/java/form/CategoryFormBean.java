package form;



public class CategoryFormBean {

	private Long id; 
	private String name;
	private String description;

	public CategoryFormBean() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long list) {
		this.id = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	};
}
