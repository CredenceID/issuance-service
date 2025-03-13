package com.credenceid.issuance.service.domain.usecase.digitalid.generate.issuer;

import com.credenceid.identity.iso18013.DataIdentifier;
import com.credenceid.identity.iso18013.mdoc.IssuerSignedItem;
import com.credenceid.issuance.service.domain.model.PersonalData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuildIssuerSignedItemsUseCaseImpl implements BuildIssuerSignedItemsUseCase {
    @Override
    public List<IssuerSignedItem> execute(PersonalData personalData, List<byte[]> randoms) {
        int index = 0;
        // Family name
        IssuerSignedItem familyNameIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.FamilyName.value, personalData.familyName());

        index++;

        // Given name
        IssuerSignedItem givenNameIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.GivenNames.value, personalData.givenNames());

        index++;

        // Issuing authority
        IssuerSignedItem issuingAuthorityIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.IssuingAuthority.value, personalData.issuer());

        index++;

        // Issuing country
        IssuerSignedItem issuingCountryIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.IssuingCountry.value, personalData.issuer());

        index++;

        // Document number
        IssuerSignedItem documentNumberIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.DocumentNumber.value, personalData.documentNumber());

        index++;

        // Date of birth
        IssuerSignedItem dateOfBirthIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.BirthDate.value, personalData.dateOfBirth());

        index++;

        // Sex
        IssuerSignedItem sexIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.Sex.value, personalData.sex().getValue());

        index++;

        // Date of expiry
        IssuerSignedItem dateOfExpiryIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.ExpiryDate.value, personalData.dateOfExpiry());

        index++;

        // Portrait
        IssuerSignedItem portraitIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.Portrait.value, personalData.portrait());

        return List.of(
                familyNameIssuerSignedItem,
                givenNameIssuerSignedItem,
                issuingAuthorityIssuerSignedItem,
                issuingCountryIssuerSignedItem,
                documentNumberIssuerSignedItem,
                dateOfBirthIssuerSignedItem,
                sexIssuerSignedItem,
                dateOfExpiryIssuerSignedItem,
                portraitIssuerSignedItem
        );
    }
}
