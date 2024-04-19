package com.megadev.toolgun.object.transfer;

import com.megadev.toolgun.object.toolgun.ToolGunStuff;

import java.util.UUID;

public interface Transfer {
    UUID getUUID();

    ToolGunStuff getStuff();
}
