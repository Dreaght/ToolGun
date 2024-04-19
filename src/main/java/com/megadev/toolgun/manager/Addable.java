package com.megadev.toolgun.manager;

import com.megadev.toolgun.object.transfer.Transfer;

public interface Addable {
    boolean onAdd(Transfer transfer);

    boolean onRemove(Transfer transfer);
}
