using UnityEngine;

using UnityEngine.UI;
public class BuildingManager : MonoBehaviour {

    public GameObject selectedShopTower;

    public Button toggleShopButton;
    public GameObject laserTowerPrefab;
    public GameObject rocketTowerPrefab;
    public GameObject empTowerPrefab;

    public Texture laserThumbnailUnselected;
    public Texture rocketThumbnailUnselected;
    public Texture empThumbnailUnselected;
    public Texture laserThumbnailSelected;
    public Texture rocketThumbnailSelected;
    public Texture empThumbnailSelected;

    public Button selectLaserTowerButton;
    public Button selectRocketTowerButton;
    public Button selectEMPTowerButton;

    public RawImage laserTowerImg;
    public RawImage rocketTowerImg;
    public RawImage empTowerImg;

    public Image shopPanel;

    bool renderShop = true;

    void ClickedLaserTowerButton (){
        selectedShopTower = laserTowerPrefab;
        laserTowerImg.texture = laserThumbnailSelected;
        rocketTowerImg.texture = rocketThumbnailUnselected;
        empTowerImg.texture = empThumbnailUnselected;
    }

    void ClickedRocketTowerButton()
    {
        selectedShopTower = rocketTowerPrefab;
        rocketTowerImg.texture = rocketThumbnailSelected;
        laserTowerImg.texture = laserThumbnailUnselected;
        empTowerImg.texture = empThumbnailUnselected;
    }

    void ClickedSelectEMPTowerButton()
    {
        selectedShopTower = empTowerPrefab;
        rocketTowerImg.texture = rocketThumbnailUnselected;
        laserTowerImg.texture = laserThumbnailUnselected;
        empTowerImg.texture = empThumbnailSelected;
    }

    void ClickedToggleShopButton()
    {
        renderShop = !renderShop;
        shopPanel.gameObject.SetActive(renderShop);

    }

    void bindButtons()
    {

        toggleShopButton.GetComponent<Button>().onClick.AddListener(ClickedToggleShopButton);


        selectLaserTowerButton.GetComponent<Button>().onClick.AddListener(ClickedLaserTowerButton);
        selectRocketTowerButton.GetComponent<Button>().onClick.AddListener(ClickedRocketTowerButton);

        selectEMPTowerButton.GetComponent<Button>().onClick.AddListener(ClickedSelectEMPTowerButton);

    }
    // Use this for initialization
    void Start () {
        bindButtons();

    }
	
	// Update is called once per frame
	void Update () {
		
	}
}
