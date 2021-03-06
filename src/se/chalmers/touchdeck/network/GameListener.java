/**
 Copyright (c) 2013 Karl Engström, Sebastian Ivarsson, Jacob Lundberg, Joakim Karlsson, Alexander Persson and Fredrik Westling
 */

/**
 This file is part of TouchDeck.

 TouchDeck is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 2 of the License, or
 (at your option) any later version.

 TouchDeck is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with TouchDeck.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.chalmers.touchdeck.network;

import java.io.Serializable;

import se.chalmers.touchdeck.game.server.GameController;
import se.chalmers.touchdeck.game.server.Operation;

/**
 * Listens to incoming connections from guiControllers, and handles their requests for operations.
 * 
 * @author group17
 */
public class GameListener extends ListenerInterface {
    private final GameController mGameController;

    /**
     * Creates a new game listener.
     * 
     * @param gc The associated GameController
     * @param port The port to listen to
     */
    public GameListener(GameController gc, int port) {
        super(true, port);
        mGameController = gc;
    }

    /**
     * Handle the operations from the guiController.
     * 
     * @param s The operation to handle
     * @param ipAddr The ip-address of the user performing the operation
     */
    @Override
    public void handle(Serializable s, String ipAddr) {
        if (s instanceof Operation) {
            Operation op = (Operation) s;
            op.setIpAddr(ipAddr);
            mGameController.performOperation(op);
        }
    }
}
