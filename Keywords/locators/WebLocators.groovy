package locators

class WebLocators {


	/*Login Page*/
	public static final String INPUT_USERNAME      = "//input[@type='text' and @name='username' and @id='username']"
	public static final String INPUT_PASSWORD      = "//input[@type='password' and @name='password' and @id='password']"
	public static final String BTN_LOGIN           = "//button[@type='submit' and contains(normalize-space(.), 'Login')]"
	public static final String LBL_ERROR_MESSAGE   = "//form/fieldset/label[2]/span"

	/*Dashboard Page*/
	public static final String MSG_LOGIN_SUCCESS = "//div[@class='span12']/div[contains(@class,'alert-success')]"
	public static final String LBL_LOGIN_INITIAL = "//span[@class='user-info'][small[text()='Login Admin']]"
	public static final String DDL_HEADER		 = "//i[@class='icon-caret-down']"
	public static final String BTN_LOGOUT 		= "//a[contains(normalize-space(.),'Keluar')]";

	// ========== Access Menu ==========
	public static final String MENU_DASHBOARD     = "//ul[@class='nav nav-list']//span[normalize-space(text())='Dashboard']";
	public static final String MENU_TAMBAH_BARU   = "//ul[@class='nav nav-list']//span[normalize-space(text())='Tambah Baru']";
	public static final String MENU_SEMUA_POS     = "//ul[@class='nav nav-list']//span[normalize-space(text())='Semua Pos']";
	public static final String MENU_GALERI        = "//ul[@class='nav nav-list']//span[normalize-space(text())='Galeri']";
	public static final String MENU_KONTAK_PESAN  = "//ul[@class='nav nav-list']//span[normalize-space(text())='Kontak Pesan']";
	public static final String MENU_KOMENTAR      = "//ul[@class='nav nav-list']//span[normalize-space(text())='Komentar']";
	public static final String MENU_PENGATURAN    = "//ul[@class='nav nav-list']//span[normalize-space(text())='Pengaturan']";
	public static final String MENU_PETUGAS       = "//ul[@class='nav nav-list']//span[normalize-space(text())='Petugas']";



	/*Admin*/
	public static final String BTN_EDIT_ADMIN = "//a[normalize-space(text())='Tambah Data']";
	public static final String BTN_TAMBAH_ADMIN = "//a[normalize-space(text())='Tambah Data']";

	//Create Admin
	public static final String INPUT_ADMIN_USERNAME   = "//input[@id='name' and @name='name']";
	public static final String INPUT_ADMIN_PASSWORD   = "//input[@id='pwd' and @name='pwd']";
	public static final String INPUT_ADMIN_EMAIL      = "//input[@id='email' and @name='email']";
	public static final String INPUT_ADMIN_FOTO       = "//input[@id='foto' and @name='foto']";

	public static final String BTN_ADMIN_SIMPAN       = "//button[@type='submit' and contains(normalize-space(.), 'Simpan')]";
	public static final String BTN_ADMIN_KELUAR       = "//a[contains(@class,'btn') and normalize-space(text())='Keluar']";
	//Table
	public static final String TBL_DATA_ADMIN   = "//table[@id='sample-table-2']";
	public static final String OBJ_EMAIL_COLUMN = "//th[normalize-space(text())='Email']";
	public static final String TBL_UPDATE_ADMIN = "//a[text()='Update']";
	public static final String TBL_DELETE_ADMIN = "//a[text()='Delete']";
}
