package it.unicam.cs.ids.UrbanUnveil.api.Enum;

import jakarta.persistence.Embeddable;

@Embeddable
public enum RoleEnum {
	MANAGER,
	CURATOR,
	TOURIST,
	SistemaOSM,
	TRUSTEDCONTRIBUTOR,
	CONTRIBUTOR,
	ANIMATORE
	
}
