package cuchaz.enigma.translation.representation.entry;

import cuchaz.enigma.source.RenamableTokenType;
import cuchaz.enigma.translation.TranslateResult;
import cuchaz.enigma.translation.Translator;
import cuchaz.enigma.translation.mapping.EntryMapping;

import javax.annotation.Nonnull;
import java.util.Objects;

public class LocalVariableIndexEntry extends LocalVariableEntry {
	private final int startScope, endScope;
	private final String type;
	public LocalVariableIndexEntry(MethodEntry parent, int index, String name, boolean parameter, String javadoc, int scopeStart, int scopeEnd, String type) {
		super(parent, index, name, parameter, javadoc);
		this.startScope = scopeStart;
		this.endScope = scopeEnd;
		this.type = type;
	}

	@Override
	protected TranslateResult<LocalVariableEntry> extendedTranslate(Translator translator, @Nonnull EntryMapping mapping) {
		String translatedName = mapping.targetName() != null ? mapping.targetName() : name;
		String javadoc = mapping.javadoc();
		return TranslateResult.of(mapping.targetName() == null ? RenamableTokenType.OBFUSCATED : RenamableTokenType.DEOBFUSCATED, new LocalVariableIndexEntry(parent, index, translatedName, parameter, javadoc, startScope, endScope, type));
	}

	@Override
	public LocalVariableEntry withName(String name) {
		return new LocalVariableIndexEntry(parent, index, name, parameter, javadocs, startScope, endScope, type);
	}

	@Override
	public LocalVariableEntry withParent(MethodEntry parent) {
		return new LocalVariableIndexEntry(parent, index, name, parameter, javadocs, startScope, endScope, type);
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(this.parent, this.index, this.startScope, this.endScope);
//	}
//
//	@Override
//	public boolean equals(LocalVariableEntry other) {
//		return other instanceof LocalVariableIndexEntry indexEntry && equals(indexEntry);
//	}
//
//	public boolean equals(LocalVariableIndexEntry other) {
//		return this.parent.equals(other.parent) && this.index == other.index && this.startScope == other.startScope && this.endScope == other.endScope;
//	}
}