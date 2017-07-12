package com.rikkabot.rikkabotcore.dao.accounts.settings;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.manulaiko.blackeye.net.game.commands.out.SETCommand;
import com.manulaiko.blackeye.net.game.commands.out.SelectLaserCommand;
import com.manulaiko.blackeye.net.game.commands.out.SelectRocketCommand;

/**
 * Settings class.
 * ===============
 * 
 * Contains account's client settings.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Settings extends DAO
{
    /**
     * Automatic weapons upgrade by activating lab CPU.
     */
    private boolean _boosten = false;

    /**
     * Show damage bar.
     */
    private boolean _dsplyDamage = false;

    /**
     * Show all lasers.
     */
    private boolean _dsplyAllLas = false;

    /**
     * Show explosions.
     */
    private boolean _dsplyExplo = false;

    /**
     * Show player names
     */
    private boolean _dsplyPlayerName = false;

    /**
     * Show company icon.
     */
    private boolean _dsplyFirmIcon = false;

    /**
     * Show transparent background.
     */
    private boolean _dsplyAlphaBg = false;

    /**
     * Show resources.
     */
    private boolean _ignoreRES = false;

    /**
     * Show bonus boxes.
     */
    private boolean _ignoreBOX = false;

    /**
     * Show jump portals.
     */
    private boolean _convertGates = false;

    /**
     * Show NPCs/enemies.
     */
    private boolean _convertOppo = false;

    /**
     * Play sound effects.
     */
    private boolean _soundOnOff = false;

    /**
     * Play music.
     */
    private boolean _bgmusicOnOff = false;

    /**
     * Show message status.
     */
    private boolean _dsplyStatus = false;

    /**
     * Show increasing hit count.
     */
    private boolean _dsplyBubble = false;

    /**
     * Selected laser.
     */
    private int _selectedLaser = 1;

    /**
     * Selected rocket.
     */
    private int _selectedRocket = 2;

    /**
     * Show ammo/HP/shields with numbers.
     */
    private boolean _dsplyDigits = false;

    /**
     * Show chat.
     */
    private boolean _dsplyChat = false;

    /**
     * Show drones.
     */
    private boolean _dsplyDrones = false;

    /**
     * Hide star chart automatically after jump.
     */
    private boolean _showStarsystem = false;

    /**
     * Show cargo boxes.
     */
    private boolean _ignoreCARGO = false;

    /**
     * Show cargo boxes which are not free.
     */
    private boolean _ignoreHostileCARGO = false;

    /**
     * Change ammo/rocket type automatically.
     */
    private boolean _autochangeAmmo = false;

    /**
     * Show "Buy now" menu for ammo on the space map.
     */
    private boolean _enableFastBuy = false;

    /**
     * Constructor.
     *
     * @param id                 Settings ID.
     * @param boosten            Automatic weapons upgrade by activating lab CPU.
     * @param dsplyDamage        Show damage bar.
     * @param dsplyAllLas        Show all lasers.
     * @param dsplyExplo         Show explosions.
     * @param dsplyPlayerName    Show player names
     * @param dsplyFirmIcon      Show company icon.
     * @param dsplyAlphaBg       Show transparent background.
     * @param ignoreRES          Show resources.
     * @param ignoreBOX          Show bonus boxes.
     * @param convertGates       Show jump portals.
     * @param convertOppo        Show NPCs/enemies.
     * @param soundOnOff         Play sound effects.
     * @param bgmusicOnOff       Play music.
     * @param dsplyStatus        Show message status.
     * @param dsplyBubble        Show increasing hit count.
     * @param selectedLaser      Selected laser.
     * @param selectedRocket     Selected rocket.
     * @param dsplyDigits        Show ammo/HP/shields with numbers.
     * @param dsplyChat          Show chat.
     * @param dsplyDrones        Show drones.
     * @param showStarsystem     Hide star chart automatically after jump.
     * @param ignoreCARGO        Show cargo boxes.
     * @param ignoreHostileCARGO Show cargo boxes which are not free.
     * @param autochangeAmmo     Change ammo/rocket type automatically.
     * @param enableFastBuy      Show "Buy now" menu for ammo on the space map.
     */
    public Settings(
            int id, boolean boosten, boolean dsplyDamage, boolean dsplyAllLas, boolean dsplyExplo,
            boolean dsplyPlayerName, boolean dsplyFirmIcon, boolean dsplyAlphaBg, boolean ignoreRES,
            boolean ignoreBOX, boolean convertGates, boolean convertOppo, boolean soundOnOff,
            boolean bgmusicOnOff, boolean dsplyStatus, boolean dsplyBubble, int selectedLaser,
            int selectedRocket, boolean dsplyDigits, boolean dsplyChat, boolean dsplyDrones,
            boolean showStarsystem, boolean ignoreCARGO, boolean ignoreHostileCARGO, boolean autochangeAmmo,
            boolean enableFastBuy
    ) {
        super(id);
        
        this._boosten            = boosten;
        this._dsplyDamage        = dsplyDamage;
        this._dsplyAllLas        = dsplyAllLas;
        this._dsplyExplo         = dsplyExplo;
        this._dsplyPlayerName    = dsplyPlayerName;
        this._dsplyFirmIcon      = dsplyFirmIcon;
        this._dsplyAlphaBg       = dsplyAlphaBg;
        this._ignoreRES          = ignoreRES;
        this._ignoreBOX          = ignoreBOX;
        this._convertGates       = convertGates;
        this._convertOppo        = convertOppo;
        this._soundOnOff         = soundOnOff;
        this._bgmusicOnOff       = bgmusicOnOff;
        this._dsplyStatus        = dsplyStatus;
        this._dsplyBubble        = dsplyBubble;
        this._selectedLaser      = selectedLaser;
        this._selectedRocket     = selectedRocket;
        this._dsplyDigits        = dsplyDigits;
        this._dsplyChat          = dsplyChat;
        this._dsplyDrones        = dsplyDrones;
        this._showStarsystem     = showStarsystem;
        this._ignoreCARGO        = ignoreCARGO;
        this._ignoreHostileCARGO = ignoreHostileCARGO;
        this._autochangeAmmo     = autochangeAmmo;
        this._enableFastBuy      = enableFastBuy;
    }

    /**
     * Builds and returns the SET command.
     *
     * @return SET command.
     */
    public SETCommand getSETCommand()
    {
        return new SETCommand(
                this.boosten(),
                this.dsplyDamage(),
                this.dsplyAllLas(),
                this.dsplyExplo(),
                this.dsplyPlayerName(),
                this.dsplyFirmIcon(),
                this.dsplyAlphaBg(),
                this.ignoreRES(),
                this.ignoreBOX(),
                this.convertGates(),
                this.convertOppo(),
                this.soundOnOff(),
                this.bgmusicOnOff(),
                this.dsplyStatus(),
                this.dsplyBubble(),
                this.dsplyDigits(),
                this.dsplyChat(),
                this.dsplyDrones(),
                this.showStarsystem(),
                this.ignoreCARGO(),
                this.ignoreHostileCARGO(),
                this.autochangeAmmo(),
                this.enableFastBuy()
        );
    }

    /**
     * Builds and returns the SelectLaser command.
     *
     * @return SelectLaser command.
     */
    public SelectLaserCommand getSelectLaserCommand()
    {
        return new SelectLaserCommand(this.selectedLaser());
    }

    /**
     * Builds and returns the SelectRocket command.
     *
     * @return SelectRocket command.
     */
    public SelectRocketCommand getSelectRocketCommand()
    {
        return new SelectRocketCommand(this.selectedRocket());
    }
}
